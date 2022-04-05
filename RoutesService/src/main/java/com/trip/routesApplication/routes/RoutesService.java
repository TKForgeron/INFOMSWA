package com.trip.routesApplication.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoutesService {

    private final RoutesRepository RoutesRepository;

    @Autowired
    public RoutesService(RoutesRepository RoutesRepository) {
        this.RoutesRepository = RoutesRepository;
    }

    public List<Route> getRoutes() {
        return RoutesRepository.findAll();
    }

    public Route findRouteByStations(String stationA, String stationB) {
        return findRouteByStations(stationA, stationB);
    }

    // public Boolean isValid(Integer nfcId) {
    // Optional<Routes> accountOptional = RoutesRepository
    // .findAccountByNfcId(nfcId);
    // if (accountOptional.isPresent()) {
    // return true;
    // }
    // return false;
    // }

    // public void addNewStudent(Routes Routes) {
    // Optional<Routes> studentOptional = RoutesRepository
    // .findStudentByEmail(Routes.getEmail());
    // if (studentOptional.isPresent()) {
    // throw new IllegalStateException("email taken");
    // }
    // RoutesRepository.save(Routes);
    // }

    // public void deleteStudent(Long studentId) {
    // boolean exists = RoutesRepository.existsById(studentId);
    // if (!exists) {
    // throw new IllegalStateException(
    // "student with id: " + studentId + "doesn't exist");
    // }
    // RoutesRepository.deleteById(studentId);
    // }

    // @Transactional
    // public void updateStudent(Long studentId,
    // String name,
    // String email) {
    // Routes Routes = RoutesRepository.findById(studentId)
    // .orElseThrow(() -> new IllegalStateException("studentID doesn't exist"));

    // if (name != null && name.length() > 0 && Routes.getName() != name) {
    // Routes.setName(name);
    // }
    // if (email != null && email.length() > 0 && Routes.getEmail() != email) {
    // Routes.setEmail(email);
    // }
    // }
}
