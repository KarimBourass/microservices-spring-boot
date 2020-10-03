package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}


	@Bean
	RouteLocator staticRoutes(RouteLocatorBuilder builder)  {
		return builder.routes()
				.route(p -> p
						.path("/currency/**")
						.filters(f->f
							.addRequestHeader("x-rapidapi-host", "currency26.p.rapidapi.com")
							.addRequestHeader("x-rapidapi-key","11b7163a78msh552d537fb758ba3p16d5fcjsn2dd80f2f7138")
							.rewritePath("/currency/(?<segment>.*)","/${segment}")
							.hystrix(h-> h.setName("currency").setFallbackUri("forward:/defaultCurrency"))

						)
						.uri("https://currency26.p.rapidapi.com/convert/EUR/USD/10")
						.id("hello"))
				.build();
		//list of all cureencys
		//http://localhost:8888/currency/list
	}


	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp)
	{
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}


}
