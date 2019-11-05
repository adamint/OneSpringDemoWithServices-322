package one;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OneService {
  public Map<Integer, String> getTransactions(HttpSession session) {
    @SuppressWarnings("unchecked")
    Map<Integer, String> transactions = (Map<Integer, String>) session.getAttribute("transactions");
    if (transactions == null) {
      transactions = new TreeMap<>();
      session.setAttribute("transactions", transactions);
    }

    return transactions;
  }

  public void addTransaction(HttpSession session, String add) {
    TreeMap<Integer, String> transactions = (TreeMap<Integer, String>) getTransactions(session);
    int lastKey = transactions.size() > 0 ? transactions.lastKey() : 0;
    transactions.put(lastKey + 1, add);
  }

  public void deleteTransaction(HttpSession session, int delete) {
    Map<Integer, String> transactions = getTransactions(session);
    transactions.remove(delete);
  }
}
