//package com.template.contracts
//
//import com.template.states.ProposalState
//import net.corda.core.contracts.CommandData
//import net.corda.core.contracts.Requirements.using
//import net.corda.core.contracts.requireSingleCommand
//import net.corda.core.contracts.requireThat
//import net.corda.core.transactions.LedgerTransaction
//import org.jetbrains.annotations.Contract
//
//class ReservationContract: Contract {
//    companion object {
//        val ID = ProposalContract::class.qualifiedName!!
//    }
//
//    // Our Create command.
//    class Create : CommandData
//
//    override fun verify(tx: LedgerTransaction) {
//        val command = tx.commands.requireSingleCommand<Create>()
//
//        requireThat {
//            // Constraints on the shape of the transaction.
//            "No inputs should be consumed when issuing an Proposal." using (tx.inputs.isEmpty())
//            "There should be one output state of type ProposalState." using (tx.outputs.size == 1)
//
//            // IOU-specific constraints.
//            val output = tx.outputsOfType<ProposalState>().single()
//            "The valuePerHour value must be non-negative." using (output.valuePerHour > 0)
//            "The volume value must be non-negative." using (output.volume > 0)
//            "The lender and the borrower cannot be the same entity." using (output.vendor != output.buyer)
//            "The dateInit cannot mayor dateFinish." using (output.dateInit < output.dateFinish)
//
//
//            // Constraints on the signers.
//            val expectedSigners = listOf(output.buyer.owningKey, output.vendor.owningKey)
//            "There must be two signers." using (command.signers.toSet().size == 2)
//            "The borrower and lender must be signers." using (command.signers.containsAll(expectedSigners))
//        }
//    }
//}