package desafio.itau.backend.repository

import desafio.itau.backend.model.Transaction
import org.springframework.stereotype.Repository
import java.util.concurrent.CopyOnWriteArrayList

@Repository
class TransactionRepository {

    private val transactions = CopyOnWriteArrayList<Transaction>()

    fun add(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun clear() {
        transactions.clear()
    }

    fun list(): List<Transaction> {
        return transactions.toList()
    }
}
