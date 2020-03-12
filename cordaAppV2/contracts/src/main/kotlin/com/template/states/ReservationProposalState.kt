package com.template.states

import com.template.contracts.ReservationProposalContract
//import com.template.contracts.TemplateContract
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.ContractState
import net.corda.core.identity.Party

// *********
// * State *
// *********
@BelongsToContract(ReservationProposalContract::class)


class ReservationProposalState(val valuePerHour: Int,
                               val vendor: Party,
                               val buyer: Party) : ContractState {
    override val participants get() = listOf(vendor, buyer)
}