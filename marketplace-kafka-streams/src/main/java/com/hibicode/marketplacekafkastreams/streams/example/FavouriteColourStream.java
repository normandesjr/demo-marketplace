package com.hibicode.marketplacekafkastreams.streams.example;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Arrays;

@Configuration
public class FavouriteColourStream {

    @Bean
    public NewTopic favouriteColourInput() {
        return TopicBuilder.name("favourite-colour-input")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userKeysAndColours() {
        return TopicBuilder.name("user-keys-and-colours")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic favouriteColourOutput() {
        return TopicBuilder.name("favourite-colour-output")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
	public KStream<String, String> kStream(StreamsBuilder builder) {
		KStream<String, String> textLines = builder.stream(favouriteColourInput().name());

		KStream<String, String> usersAndColours = textLines
				.filter((key, value) -> value.contains(","))
				.selectKey((key, value) -> value.split(",")[0].toLowerCase())
				.mapValues(value -> value.split(",")[1].toLowerCase())
				.filter((user, colour) -> Arrays.asList("green", "blue", "red").contains(colour));

		usersAndColours.to(userKeysAndColours().name());

		// read that topic as a KTable so that updates are read correctly
		KTable<String, String> usersAndColoursTable = builder.table(userKeysAndColours().name());

		KTable<String, Long> favouriteColours = usersAndColoursTable
				.groupBy((user, colour) -> new KeyValue<>(colour, colour))
				.count(Materialized.with(Serdes.String(), Serdes.Long()));

		favouriteColours.toStream().to(favouriteColourOutput().name(), Produced.with(Serdes.String(), Serdes.Long()));

		return textLines;
	}
}
