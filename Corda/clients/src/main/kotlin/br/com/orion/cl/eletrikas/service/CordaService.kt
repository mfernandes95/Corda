package br.com.orion.cl.eletrikas.service

import br.com.orion.cl.eletrikas.NodeRPCConnection
import net.corda.core.messaging.startTrackedFlow
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.PublicKey
import java.util.*

@Service
class CordaService(rpc: NodeRPCConnection) {

    val proxy = rpc.proxy

    @Value("\${service.id}")
    lateinit var serviceId: UUID


}