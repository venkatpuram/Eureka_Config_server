package com.moneymoney.web.controller;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoney.web.entity.CurrentDataSet;
import com.moneymoney.web.entity.Transaction;

@RefreshScope
@Controller
public class BankAppController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public String indexForm() {
		return "index";
	}

	@RequestMapping("/deposit")
	public String depositForm() {
		return "DepositForm";
	}

	@RequestMapping("/depositpage")
	public String deposit(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://transaction-service/transactions", transaction, null);
		model.addAttribute("message", "Deposit Success!");
		return "DepositForm";
	}

	@RequestMapping("/withdraw")
	public String withdrawForm() {
		return "withdrawForm";
	}

	@RequestMapping("/withdrawpage")
	public String withdraw(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://transaction-service/transactions/withdraw", transaction, null);
		model.addAttribute("message", " Withdraw Success!");
		return "withdrawForm";
	}

	@RequestMapping("/fundtransfer")
	public String fundTransferForm() {
		return "FundTransferForm";
	}

	@RequestMapping("/fundtransferpage")
	public String fundTransfer(@RequestParam("senderaccountNumber") int senderaccountNumber,
			@RequestParam("receiveraccountNumber") int receiveraccountNumber, @ModelAttribute Transaction transaction,
			Model model) {
		transaction.setAccountNumber(senderaccountNumber);
		restTemplate.postForEntity("http://transaction-service/transactions/withdraw", transaction, null);
		transaction.setAccountNumber(receiveraccountNumber);
		restTemplate.postForEntity("http://transaction-service/transactions", transaction, null);
		model.addAttribute("message", "Transfer Success!");
		return "FundTransferForm";
	}

	@RequestMapping("/getstatement")
	public ModelAndView getAllStatements(@RequestParam("offset") int offset, @RequestParam("size") int size) {
		CurrentDataSet currentDataSet = restTemplate.getForObject("http://transaction-service/transactions/statements",
				CurrentDataSet.class);
		int currentSize = size == 0 ? 5 : size;
		int currentOffSet = offset == 0 ? 1 : offset;
		Link next = linkTo(methodOn(BankAppController.class).getAllStatements(currentOffSet + currentSize, currentSize))
				.withRel("next");
		Link previous = linkTo(
				methodOn(BankAppController.class).getAllStatements(currentOffSet - currentSize, currentSize))
						.withRel("previous");
		List<Transaction> transactions = currentDataSet.getTransactions();
		List<Transaction> currentDataSetList = new ArrayList<Transaction>();
		for (int i = currentOffSet - 1; i < currentSize + currentOffSet - 1; i++) {
			if ((transactions.size() <= i && i > 0) || currentOffSet < 1)
				break;
			Transaction transaction = transactions.get(i);
			currentDataSetList.add(transaction);
		}
		CurrentDataSet dataSet = new CurrentDataSet(currentDataSetList, next, previous);
		return new ModelAndView("getstatement", "currentDataSet", dataSet);
	}
}
