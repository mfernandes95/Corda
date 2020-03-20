package br.com.orion.cl.eletrikas

import net.corda.client.jackson.JacksonSupport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@SpringBootApplication
class EletrikasAplication {
    /**
     * Spring Bean that binds a Corda Jackson object-mapper to HTTP message types used in Spring.
     */
    @Bean
    open fun mappingJackson2HttpMessageConverter(@Autowired rpcConnection: NodeRPCConnection): MappingJackson2HttpMessageConverter {
        val mapper = JacksonSupport.createDefaultMapper(rpcConnection.proxy)
        val converter = MappingJackson2HttpMessageConverter()
        converter.objectMapper = mapper
        return converter
    }
}


fun main(args: Array<String>) {
    runApplication<EletrikasAplication>(*args)
}
