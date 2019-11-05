package one;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class OneController {
  private final OneService oneService;

  public OneController(OneService oneService) {
    this.oneService = oneService;
  }

  @GetMapping("/")
  public String oneHome(HttpSession session, Model model) {
    Map<Integer, String> transactions = oneService.getTransactions(session);
    model.addAttribute("transactions", transactions);
    model.addAttribute("transactionNumber", transactions == null ? 0 : transactions.size());

    return "index";
  }

  @PostMapping("/add")
  public String add(@RequestParam("add") String add, HttpSession session) {
    oneService.addTransaction(session, add);

    return "redirect:/";
  }

  @PostMapping("/delete")
  public String delete(@RequestParam("delete") int deleteKey, HttpSession session) {
    oneService.deleteTransaction(session, deleteKey);
    return "redirect:/";
  }
}
