package com.template.contracts

import com.template.states.ReservationProposalState
import net.corda.core.contracts.CommandData
import net.corda.core.contracts.Contract
import net.corda.core.transactions.LedgerTransaction

import net.corda.core.contracts.*

// ************
// * Contract *
// ************
class ReservationProposalContract : Contract {
    companion object {
        // Used to identify our contract when building a transaction.
//        const val ID = "com.template.contracts.IOUContract"
        val ID = ReservationProposalContract::class.qualifiedName!!
    }

    // Our Create command.
    class Create : CommandData

    override fun verify(tx: LedgerTransaction) {
        val command = tx.commands.requireSingleCommand<Create>()

        requireThat {
            // Constraints on the shape of the transaction.
            "No inputs should be consumed when issuing an ReservationProposal." using (tx.inputs.isEmpty())
            "There should be one output state of type ReservationProposalState." using (tx.outputs.size == 1)

            // IOU-specific constraints.
            val output = tx.outputsOfType<ReservationProposalState>().single()
            "The valuePerHour value must be non-negative." using (output.valuePerHour > 0)
            "The volume value must be non-negative." using (output.volume > 0)
            "The lender and the borrower cannot be the same entity." using (output.vendor != output.buyer)
            "The dateInit cannot mayor dateFinish." using (output.dateInit < output.dateFinish)


            // Constraints on the signers.
            val expectedSigners = listOf(output.buyer.owningKey, output.vendor.owningKey)
            "There must be two signers." using (command.signers.toSet().size == 2)
            "The borrower and lender must be signers." using (command.signers.containsAll(expectedSigners))
        }
    }
}

//    // A transaction is valid if the verify() function of the contract of all the transaction's input and output states
//    // does not throw an exception.
//    override fun verify(tx: LedgerTransaction) {
//        // Verification logic goes here.
//    }
//
//    // Used to indicate the transaction's intent.
//    interface Commands : CommandData {
//        class Action : Commands
//    }
//}