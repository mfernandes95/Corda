//package com.template.flows
//
//import co.paralleluniverse.fibers.Suspendable
//import com.template.contracts.ReservationProposalContract
//import com.template.states.ReservationProposalState
//import net.corda.core.contracts.Command
//import net.corda.core.contracts.Requirements.using
//import net.corda.core.contracts.requireThat
//import net.corda.core.flows.*
//import net.corda.core.identity.Party
//import net.corda.core.transactions.SignedTransaction
//import net.corda.core.transactions.TransactionBuilder
//import net.corda.core.utilities.ProgressTracker
//import java.util.*
//
//
//@InitiatingFlow
//@StartableByRPC
//class ProposalFlow(
//        //Here recive output ReservationProposalState
//) : FlowLogic<Unit>() {
//
//    /** The progress tracker provides checkpoints indicating the progress of the flow to observers. */
//    override val progressTracker = ProgressTracker()
//
//    /** The flow logic is encapsulated within the call() method. */
//    @Suspendable
//    override fun call() {
//        // We retrieve the notary identity from the network map.
//        val notary = serviceHub.networkMapCache.notaryIdentities[0]
//
//        val outputState = ReservationProposalState(/**/)
//
//        val command = Command(ReservationProposalContract.Create(), listOf(ourIdentity.owningKey, otherParty.owningKey))
//
//        // We create a transaction builder and add the components.
//        val txBuilder = TransactionBuilder(notary = notary)
//                .addOutputState(outputState, ReservationProposalContract.ID)
//                .addCommand(command)
//
//// Verifying the transaction.
//        txBuilder.verify(serviceHub)
//
//// Signing the transaction.
//        val signedTx = serviceHub.signInitialTransaction(txBuilder)
//
//// Creating a session with the other party.
//        val otherPartySession = initiateFlow(otherParty)
//
//// Obtaining the counterparty's signature.
//        val fullySignedTx = subFlow(CollectSignaturesFlow(signedTx, listOf(otherPartySession), CollectSignaturesFlow.tracker()))
//
//// Finalising the transaction.
//        subFlow(FinalityFlow(fullySignedTx, otherPartySession))
//    }
//}
//
//@InitiatedBy(ProposalFlow::class)
//class ProposalFlowResponder(private val otherPartySession: FlowSession) : FlowLogic<Unit>() {
//    @Suspendable
//    override fun call() {
//        val signTransactionFlow = object : SignTransactionFlow(otherPartySession) {
//            override fun checkTransaction(stx: SignedTransaction) = requireThat {
//                val output = stx.tx.outputs.single().data
//                "This must be an ReservatonProposal transaction." using (output is ProposalState)
//            }
//        }
//        val expectedTxId = subFlow(signTransactionFlow).id
//        subFlow(ReceiveFinalityFlow(otherPartySession, expectedTxId))
//    }
//}
//
