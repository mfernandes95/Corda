package com.template.states


//import com.example.contract.IOUContract
import com.template.contracts.ReservationProposalContract
import com.template.schema.ReservationProposalSchemaV1
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.ContractState
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party
import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import net.corda.core.schemas.QueryableState


// *********
// * State *
// *********
/**
 * The state object recording IOU agreements between two parties.
 *
 * A state must implement [ContractState] or one of its descendants.
 *
 * @param value the value of the IOU.
 * @param lender the party issuing the IOU.
 * @param borrower the party receiving and approving the IOU.
 */
@BelongsToContract(ReservationProposalContract::class)
data class ReservationProposalState(
                    val value: Int,
                    val lender: Party,
                    val borrower: Party,
                    override val linearId: UniqueIdentifier = UniqueIdentifier()):
        LinearState, QueryableState {
    /** The public keys of the involved parties. */
    override val participants: List<AbstractParty> get() = listOf(lender, borrower)

    override fun generateMappedObject(schema: MappedSchema): PersistentState {
        return when (schema) {
            is ReservationProposalSchemaV1 -> ReservationProposalSchemaV1.PersistentIOU(
                    this.lender.name.toString(),
                    this.borrower.name.toString(),
                    this.value,
                    this.linearId.id
            )
            else -> throw IllegalArgumentException("Unrecognised schema $schema")
        }
    }

    override fun supportedSchemas(): Iterable<MappedSchema> = listOf(ReservationProposalSchemaV1)
}
