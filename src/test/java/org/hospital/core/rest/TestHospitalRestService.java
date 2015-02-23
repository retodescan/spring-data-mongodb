package org.hospital.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.hospital.core.HospitalCoreApplication;
import org.hospital.core.domain.Hospital;
import org.hospital.core.repository.HospitalRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HospitalCoreApplication.class)
@WebAppConfiguration
public class TestHospitalRestService {

    @Autowired private WebApplicationContext wac;
    @Autowired private HospitalRepository hospitalRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Hospital hospital;

    @Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        objectMapper = new ObjectMapper();

        hospital = new Hospital(
                new ObjectId(new Date()),
                "Hospital Santa Catarina",
                "Avenida Paulista, 200 - Bela Vista",
                new Point(-23.569871,-46.644953),
                "Sao Paulo",
                "Sao Paulo",
                "Brasil");
    }

    @After
    public void tearDown() {
        hospitalRepository.deleteAll();
    }

    @Test
    public void insertHospital() throws Exception {
        String json = objectMapper.writeValueAsString(hospital);

        this.mockMvc.perform(post("/hospital").accept(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void getHospital() throws Exception {
        this.mockMvc.perform(get("/hospital").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andDo(print());
    }

    @Test
    public void getHospitalsNearby() {
        hospitalRepository.save(hospital);
        List<Hospital> hospitals = hospitalRepository.findByLocationNear(new Point(-23.571763, -46.644059), new Distance(5, Metrics.KILOMETERS));
        assertThat(hospitals, hasSize(1));
    }
}