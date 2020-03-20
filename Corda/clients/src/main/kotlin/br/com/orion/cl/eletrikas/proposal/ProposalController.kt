package br.com.orion.cl.eletrikas.proposal

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/proposal")
class ProposalController() {

    @PostMapping("/reservationproposal")
    fun createReservationProposal() {
        /*
        comands
        * */
    }

}