package com.car.rentservice.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.car.rentservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.rentservice.modal.Car;
import com.car.rentservice.modal.Comments;
import com.car.rentservice.modal.PickUpPlace;
import com.car.rentservice.modal.Reservation;
import com.car.rentservice.modal.User;
import com.car.rentservice.repositories.CarRepository;
import com.car.rentservice.repositories.CommentRepository;
import com.car.rentservice.repositories.PickUpPlaceRepository;
import com.car.rentservice.repositories.ReservationRepository;
import com.car.rentservice.repositories.UserRepository;
import com.car.rentservice.service.ilCarroService;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.ws.Response;

@Service
public class ilCarroServiceImpl implements ilCarroService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private PickUpPlaceRepository pickUpPlaceRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	@Transactional
	public UserSuccessResponseDTO updateUser(String email, UpdateUserInputDTO updateUserInputDTO) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return null;
		}
		user.setFirstName(updateUserInputDTO.getFirstName());
		user.setSecondName(updateUserInputDTO.getSecondName());
		user.setPhone(updateUserInputDTO.getPhone());
		user.setPhotoUrl(updateUserInputDTO.getPhotoUrl());

		userRepository.save(user);
		return toUserSuccessResponseDto(user);
	}

	@Override
	@Transactional
	public String deleteUser(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return "User not found";
		} else {
			List<Car> cars = carRepository.findByUserId(user.getId());
			if (cars != null && !cars.isEmpty()) {
				for (Car car : cars) {
					deleteCar(car);
					System.out.println("Car id :" + car.getId() + " is deleted");
				}
			}

			List<Comments> comments = commentRepository.findByUserId(user.getId());
			if (comments != null && !comments.isEmpty()) {
				for (Comments comment : comments) {
					commentRepository.delete(comment);
					System.out.println("Comment id :" + comment.getId() + " is deleted");
				}
			}

			userRepository.delete(user);
		}
		return "User deleted";
	}

	@Override
	@Transactional
	public CarOwnerOutputDTO addCar(String email, CarInputDTO carInputDTO) {
		Car car = new Car();

		User user = userRepository.findById(carInputDTO.getUserId()).orElse(null);
		PickUpPlace pickUpPlace = new PickUpPlace();
		pickUpPlace.setPlaceId(carInputDTO.getPickUpPlace().getPlaceId());
		pickUpPlace.setPlaceName(carInputDTO.getPickUpPlace().getPlaceName());
		pickUpPlace.setLatitude(carInputDTO.getPickUpPlace().getLatitude());
		pickUpPlace.setLongitude(carInputDTO.getPickUpPlace().getLongitude());
		pickUpPlaceRepository.save(pickUpPlace);

		car.setSerialNumber(carInputDTO.getSerialNumber());
		car.setMake(carInputDTO.getMake());
		car.setModal(carInputDTO.getModal());
		car.setYear(carInputDTO.getYear());
		car.setEngine(carInputDTO.getEngine());
		car.setFuel(carInputDTO.getFuel());
		car.setGear(carInputDTO.getGear());
		car.setWheelsDrive(carInputDTO.getWheelsDrive());
		car.setDoors(carInputDTO.getDoors());
		car.setSeats(carInputDTO.getSeats());
		car.setFuelConsumption(carInputDTO.getFuelConsumption());
		car.setFeatures(carInputDTO.getFeatures());
		car.setPricePerDay(carInputDTO.getPricePerDay());
		car.setDistanceIncluded(carInputDTO.getDistanceIncluded());
		car.setAbout(carInputDTO.getAbout());
		car.setPickUpPlace(pickUpPlace);
		car.setImageUrl(carInputDTO.getImageUrl());
		car.setUser(user);

		carRepository.save(car);

		userRepository.save(user);
		return toCarOwnerDto(car);
	}

	@Override
	@Transactional
	public ResponseModel updateCar(String email, String serialNumber, CarInputDTO carInputDTO) {

		ResponseModel responseModel = new ResponseModel();
		User user = userRepository.findByEmail(email);
		Car car = carRepository.findBySerialNumber(serialNumber).orElse(null);
		// Checking if new serial number is exists for another car or not
		Car updatedCar = carRepository.findBySerialNumber(carInputDTO.getSerialNumber()).orElse(null);
		if (updatedCar != null && updatedCar.getSerialNumber() == car.getSerialNumber()
				&& car.getId() != updatedCar.getId()) {
			responseModel.setStatus(HttpStatus.CONFLICT.toString());
			responseModel.setMessage("Serial Number is already");
			responseModel.setDataList(null);
			return responseModel;
		} else {
			if (car.getUser().getId() == user.getId()) {
				PickUpPlace pickUpPlace = new PickUpPlace();
				pickUpPlace.setPlaceId(carInputDTO.getPickUpPlace().getPlaceId());
				pickUpPlace.setPlaceName(carInputDTO.getPickUpPlace().getPlaceName());
				pickUpPlace.setLatitude(carInputDTO.getPickUpPlace().getLatitude());
				pickUpPlace.setLongitude(carInputDTO.getPickUpPlace().getLongitude());
				pickUpPlaceRepository.save(pickUpPlace);

				car.setSerialNumber(carInputDTO.getSerialNumber());
				car.setMake(carInputDTO.getMake());
				car.setModal(carInputDTO.getModal());
				car.setYear(carInputDTO.getYear());
				car.setEngine(carInputDTO.getEngine());
				car.setFuel(carInputDTO.getFuel());
				car.setGear(carInputDTO.getGear());
				car.setWheelsDrive(carInputDTO.getWheelsDrive());
				car.setDoors(carInputDTO.getDoors());
				car.setSeats(carInputDTO.getSeats());
				car.setFuelConsumption(carInputDTO.getFuelConsumption());
				car.setFeatures(carInputDTO.getFeatures());
				car.setPricePerDay(carInputDTO.getPricePerDay());
				car.setDistanceIncluded(carInputDTO.getDistanceIncluded());
				car.setAbout(carInputDTO.getAbout());
				car.setPickUpPlace(pickUpPlace);
				car.setImageUrl(carInputDTO.getImageUrl());
				car.setUser(user);
				carRepository.save(car);
				responseModel.setStatus(HttpStatus.OK.toString());
				responseModel.setMessage("Car has been updated");
				responseModel.setDataList(new ArrayList<Object>(Arrays.asList(toCarOwnerDto(car))));
				return responseModel;
			} else {
				responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
				responseModel.setMessage("You are not authorized");
				responseModel.setDataList(null);
				return responseModel;
			}
		}
	}

	@Override
	public ResponseModel getCarBySerialNumber(String serialNumber) {
		ResponseModel responseModel = new ResponseModel();
			Car car = carRepository.findBySerialNumber(serialNumber).orElse(null);
			if (car == null) {
				responseModel.setStatus(HttpStatus.NOT_FOUND.toString());
				responseModel.setMessage("Car Not Found.");
				responseModel.setDataList(null);
			} else {
				responseModel.setStatus(HttpStatus.OK.toString());
				responseModel.setDataList(new ArrayList<>(Arrays.asList(toCarOwnerDto(car))));
			}

		return responseModel;
	}

	@Override
	public ResponseModel getOwnerCars(String email) {
		ResponseModel responseModel = new ResponseModel();
			User user = userRepository.findByEmail(email);
			List<Car> ownCars = carRepository.findByUserId(user.getId());
			if (ownCars == null) {
				responseModel.setStatus(HttpStatus.NOT_FOUND.toString());
				responseModel.setMessage("No Cars");
				responseModel.setDataList(null);
			} else {
				responseModel.setStatus(HttpStatus.OK.toString());
				responseModel.setMessage(user.getFirstName() + user.getSecondName() + " cars will displayed down.");
				responseModel.setDataList(new ArrayList<>(toCarListOutputDTO(ownCars)));
			}
		return responseModel;
	}

	@Override
	public ResponseModel getOwnerCarBySerialNumber(String email, String serialNumber) {
		ResponseModel responseModel = new ResponseModel();
		User user = userRepository.findByEmail(email);
		Car car = carRepository.findBySerialNumber(serialNumber).orElseThrow(() ->
				new ResponseStatusException(
						HttpStatus.NOT_FOUND,"Car Not Exists"));
		if(user.getCars().contains(car)) {
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setMessage("Car with serialNumber " + car.getSerialNumber());
			responseModel.setDataList(new ArrayList<>(Arrays.asList(toCarOutputDto(car))));
		} else {
			responseModel.setDataList(null);
			responseModel.setMessage("Car is own by some one else .");
			responseModel.setStatus(HttpStatus.CONFLICT.toString());
		}
		return responseModel;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseModel getOwnerBookedPeriodsBySerialNumber(String email,String serialNumber) {
		ResponseModel responseModel = new ResponseModel();
		User user = userRepository.findByEmail(email);
		List<Reservation> bookedPeriods = reservationRepository.findBySerialNumber(serialNumber);
		List<Reservation> reservations = bookedPeriods.stream()
				.filter(b -> b.getUser().getEmail().equals(user.getEmail()))
				.collect(Collectors.toList());
		if(!reservations.isEmpty() && reservations != null) {
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setDataList(new ArrayList<>(toBookedListPeriodsDto(reservations)));
			responseModel.setMessage("Booked Periods of serialNumber " + serialNumber);
		} else {
			responseModel.setStatus(HttpStatus.NOT_FOUND.toString());
			responseModel.setMessage("Booked Periods not Found for this serialNumber "+serialNumber);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getLatestComments() {
		List<Comments> comments = commentRepository.findAll().stream()
				.sorted(Comparator.comparing(Comments::getCreatedAt).reversed())
				.limit(6)
				.collect(Collectors.toList());
		ResponseModel responseModel = new ResponseModel();
		responseModel.setMessage("6 last comments");
		responseModel.setStatus(HttpStatus.OK.toString());
		responseModel.setDataList(new ArrayList<>(toCommentsListOutputDto(comments)));
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel addComment(String email, String serialNumber, CommentInputDTO commentInputDTO) {
		ResponseModel responseModel = new ResponseModel();
		User commenter = userRepository.findByEmail(email);//logged in user.
		Car car = carRepository.findByUserIdAndSerialNumber(commentInputDTO.getUserId(),serialNumber);
		Comments comment = new Comments();
		comment.setUser(commenter);
		comment.setPost(commentInputDTO.getPost());
		comment.setSerialNumber(car.getSerialNumber());

		commentRepository.save(comment);

		userRepository.save(commenter);

		responseModel.setStatus(HttpStatus.OK.toString());
		responseModel.setDataList(new ArrayList<Object>(Arrays.asList(toCommentsOutputDto(comment))));
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel deleteCar(String email, String serialNumber) {
		ResponseModel responseModel = new ResponseModel();
		User user = userRepository.findByEmail(email);
		List<Reservation> reservationList = reservationRepository.findBySerialNumber(serialNumber);
		for (Reservation reservation : reservationList) {
			if (LocalDateTime.now().isBefore(reservation.getEndDateTime())) {
				responseModel.setStatus(HttpStatus.CONFLICT.toString());
				responseModel.setMessage(
						"Car with Serial Number" + serialNumber + "cannot be deleted, as it is already reserved");
				return null;
			}
		}
		Car car = carRepository.findBySerialNumber(serialNumber).orElse(null);
		if (car.getUser().getId() == user.getId()) {
			deleteCar(car);
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setMessage("Car with pickup place is deleted");
			return responseModel;
		} else {
			responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
			responseModel.setMessage("You are not authorized");
			return responseModel;
		}
	}

	private void deleteCar(Car car) {
		PickUpPlace pickUpPlace = car.getPickUpPlace();
		carRepository.delete(car);
		pickUpPlaceRepository.delete(pickUpPlace);
	}

	private CarOwnerOutputDTO toCarOwnerDto(Car car) {
		return new CarOwnerOutputDTO(car.getSerialNumber(), car.getMake(), car.getModal(), car.getYear(),
				car.getEngine(), car.getFuel(), car.getGear(), car.getWheelsDrive(), car.getDoors(), car.getSeats(),
				car.getFuelConsumption(), car.getFeatures(), car.getCarClass(), car.getPricePerDay(),
				car.getDistanceIncluded(), car.getAbout(), toPickUpPlaceDto(car.getPickUpPlace()), car.getImageUrl(),
				new OwnerOutputDTO(car.getUser().getFirstName(), car.getUser().getSecondName(),
						car.getUser().getCreatedAt()),
				toBookedListPeriodsDto(car.getUser().getReservations()));
	}

	private UserSuccessResponseDTO toUserSuccessResponseDto(User newUser) {
		return new UserSuccessResponseDTO(newUser.getFirstName(), newUser.getSecondName(), newUser.getCreatedAt(),
				toCommentsListOutputDto(newUser.getComments()), toCarListOutputDTO(newUser.getCars()),
				toBookedListCarsOutputDto(newUser.getReservations()),
				toBookedListCarsOutputDto(newUser.getReservations()));
	}

	private List<BookedCarsOutputDTO> toBookedListCarsOutputDto(List<Reservation> reservations) {
		return reservations.stream().map(this::toBookedCarsOutputDto).collect(Collectors.toList());
	}

	private BookedCarsOutputDTO toBookedCarsOutputDto(Reservation reservation) {
		return new BookedCarsOutputDTO(reservation.getSerialNumber(),
				toBookedListPeriodDto(reservation.getUser().getReservations()));
	}

	private List<BookedPeriodDTO> toBookedListPeriodDto(List<Reservation> reservation) {
		return reservation.stream().map(this::toBookedPeriodDto).collect(Collectors.toList());
	}

	private BookedPeriodDTO toBookedPeriodDto(Reservation reservation) {
		return new BookedPeriodDTO(reservation.getOrderNumber(), reservation.getStartDateTime(),
				reservation.getEndDateTime(), reservation.isPaid(), reservation.getAmount(),
				reservation.getBookingDate());
	}

	private List<CarOutputDTO> toCarListOutputDTO(List<Car> cars) {
		return cars.stream().map(this::toCarOutputDto).collect(Collectors.toList());
	}

	private CarOutputDTO toCarOutputDto(Car car) {
		return new CarOutputDTO(car.getSerialNumber(), car.getMake(), car.getModal(), car.getYear(), car.getEngine(),
				car.getFuel(), car.getGear(), car.getWheelsDrive(), car.getDoors(), car.getSeats(),
				car.getFuelConsumption(), car.getFeatures(), car.getCarClass(), car.getPricePerDay(),
				car.getDistanceIncluded(), car.getAbout(), toPickUpPlaceDto(car.getPickUpPlace()), car.getImageUrl(),
				toBookedListPeriodsDto(car.getUser().getReservations()));
	}

	private List<BookedPeriodsDTO> toBookedListPeriodsDto(List<Reservation> reservations) {
		return reservations.stream().map(this::toBookedPeriodsDto).collect(Collectors.toList());
	}

	private BookedPeriodsDTO toBookedPeriodsDto(Reservation reservation) {
		return new BookedPeriodsDTO(reservation.getOrderNumber(), reservation.getStartDateTime(),
				reservation.getEndDateTime(), reservation.isPaid(), reservation.getAmount(),
				reservation.getBookingDate(), toBookedPersonDto(reservation.getUser()));
	}

	private BookedPersonOutputDTO toBookedPersonDto(User user) {
		return new BookedPersonOutputDTO(user.getEmail(), user.getFirstName(), user.getSecondName(), user.getPhone());
	}

	private PickUpPlaceDTO toPickUpPlaceDto(PickUpPlace pickUpPlace) {
		return new PickUpPlaceDTO(pickUpPlace.getPlaceId(), pickUpPlace.getPlaceName(), pickUpPlace.getLatitude(),
				pickUpPlace.getLongitude());
	}

	private List<CommentsOutputDTO> toCommentsListOutputDto(List<Comments> comments) {
		return comments.stream().map(this::toCommentsOutputDto).collect(Collectors.toList());
	}

	private CommentsOutputDTO toCommentsOutputDto(Comments comments) {
		return new CommentsOutputDTO(comments.getUser().getFirstName(), comments.getUser().getSecondName(),
				comments.getUser().getPhotoUrl(), comments.getCreatedAt(), comments.getPost());
	}
}
