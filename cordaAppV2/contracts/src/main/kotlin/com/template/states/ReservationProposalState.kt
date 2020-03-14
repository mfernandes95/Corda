package com.template.states

import com.template.contracts.ReservationProposalContract
//import com.template.contracts.TemplateContract
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.ContractState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.Party
import java.util.*

// *********
// * State *
// *********
@BelongsToContract(ReservationProposalContract::class)


class ReservationProposalState(val id: UniqueIdentifier,
                               val valuePerHour: Double,
                               val volume: Int,
                               val value: Double,
                               val dateInit: Date,
                               val dateFinish: Date,
                               val energyType: String,
                               val deliveryPoint: String,
                               val vendorOperator: String,
                               val createdAt: Date,
                               val status: String,
                               val vendorSignature: Date,
                               val vendor: Party,
                               val buyer: Party) : ContractState {
    override val participants get() = listOf(vendor, buyer)
}