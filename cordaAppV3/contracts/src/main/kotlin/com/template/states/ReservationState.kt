//package com.template.states
//
//import com.template.contracts.ProposalContract
//import net.corda.core.contracts.BelongsToContract
//import net.corda.core.contracts.ContractState
//import net.corda.core.contracts.UniqueIdentifier
//import net.corda.core.identity.Party
//import java.util.*
//
//
//@BelongsToContract(ProposalContract::class)
//
//class ProposalState(/**/) : ContractState {
//    override val participants get() = listOf(vendor, buyer)
//}