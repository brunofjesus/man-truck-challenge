package pt.brunojesus.truck.persistence.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pt.brunojesus.truck.model.domain.Truck;
import pt.brunojesus.truck.persistence.repository.ITruckRepository;

@Component
public class DbSeeder implements CommandLineRunner {

	@Autowired
	private ITruckRepository truckRepository;
	
	@Override
	public void run(String... args) throws Exception {
//		Truck tgx18440 = Truck.builder().model("TGX 18.440").displacement(18.518).horsepower(440).build();
		
//		truckRepository.save(tgx18440);
	}

}
