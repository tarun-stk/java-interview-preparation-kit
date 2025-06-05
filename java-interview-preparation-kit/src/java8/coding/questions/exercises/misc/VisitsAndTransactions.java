package java8.coding.questions.exercises.misc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class VisitsAndTransactions {
    /*given list of visits and transactions
    * visit_id is the column with unique values for this table.
This table contains information about the customers who visited the mall.
* transaction_id is column with unique values for this table.
This table contains information about the transactions made during the visit_id.
*
* Write a solution to find the IDs of the users who visited without making any transactions and the number of times they made these types of visits.*/

    public static void main(String[] args) {
        List<Visit> visits = List.of(
                new Visit(1, 23),
                new Visit(2, 9),
                new Visit(4, 30),
                new Visit(5, 54),
                new Visit(6, 96),
                new Visit(7, 54),
                new Visit(8, 54)
        );

        List<Transaction> transactions = List.of(
                new Transaction(2, 5, 310),
                new Transaction(3, 5, 300),
                new Transaction(9, 5, 200),
                new Transaction(12, 1, 910),
                new Transaction(13, 2, 970)
        );

        solve(visits, transactions);
    }

    private static void solve(List<Visit> visits, List<Transaction> transactions) {
        Set<Integer> visitIdsFromTransactions = transactions.stream().map(Transaction::getVisitId).collect(Collectors.toSet());

        visits.stream().collect(Collectors.groupingBy(Visit::getCustomerId, Collectors.mapping(Visit::getVisitId, Collectors.toSet())))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().
                        filter(visitId -> !visitIdsFromTransactions.contains(visitId)).count()))
                .entrySet().stream().filter(entry -> entry.getValue() > 0).forEach(System.out::println);

    }
}

class Visit {
    int visitId;
    int customerId;

    public Visit(int visitId, int customerId) {
        this.visitId = visitId;
        this.customerId = customerId;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

class Transaction {
    int transactionId;
    int visitId;
    int amount;

    public Transaction(int transactionId, int visitId, int amount) {
        this.transactionId = transactionId;
        this.visitId = visitId;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

class Result {
    int customerId;
    long countOfVisits;

    public Result(int customerId, long countOfVisits) {
        this.customerId = customerId;
        this.countOfVisits = countOfVisits;
    }

    @Override
    public String toString() {
        return "Result{" +
                "customerId=" + customerId +
                ", countOfVisits=" + countOfVisits +
                '}';
    }
}
