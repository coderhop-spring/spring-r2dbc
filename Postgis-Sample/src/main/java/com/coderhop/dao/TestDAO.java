package com.coderhop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import io.r2dbc.spi.Row;
import reactor.core.publisher.Flux;

@Component
public class TestDAO {

	@Autowired
	private R2dbcEntityTemplate r2dbcEntityTemplate;

	public Flux<StoreData> execute() {
		DatabaseClient databaseClient = r2dbcEntityTemplate.getDatabaseClient();
		String sql="select * from ultradev.costco_location_data cs where ST_Intersects(cs.location,ST_GeomFromText('point(-88.5529 34.2728)',4326)) and id=1";
		System.out.println("Executing sql "+sql);
		return databaseClient.sql(
				sql)
				.map(this::rowMapper).all();

	}

	private StoreData rowMapper(Row row) {

		return new StoreData(row.get("id", Integer.class), row.get("location", String.class),
				row.get("shape", String.class), row.get("name", String.class));

	}
}
