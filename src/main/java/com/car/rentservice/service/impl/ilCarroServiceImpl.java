package com.car.rentservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.rentservice.dto.BookedCarsOutputDTO;
import com.car.rentservice.dto.BookedPeriodDTO;
import com.car.rentservice.dto.BookedPeriodsDTO;
import com.car.rentservice.dto.BookedPersonOutputDTO;
import com.car.rentservice.dto.CarInputDTO;
import com.car.rentservice.dto.CarOutputDTO;
import com.car.rentservice.dto.CarOwnerOutputDTO;
import com.car.rentservice.dto.CommentsOutputDTO;
import com.car.rentservice.dto.OwnerOutputDTO;
import com.car.rentservice.dto.PickUpPlaceDTO;
import com.car.rentservice.dto.UpdateUserInputDTO;
import com.car.rentservice.dto.UserSuccessResponseDTO;
import com.car.rentservice.modal.Car;
import com.car.rentservice.modal.Comments;
import com.car.rentservice.modal.PickUpPlace;
import com.car.rentservice.modal.Reservation;
import com.car.rentservice.modal.User;
import com.car.rentservice.repositories.CarRepository;
import com.car.rentservice.repositories.CommentRepository;
import com.car.rentservice.repositories.PickUpPlaceRepository;
import com.car.rentservice.repositories.UserRepository;
import com.car.rentservice.service.ilCarroService;

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
					carRepository.delete(car);
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
	public String deleteCar(String email, String serialNumber) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return null;
		}
		return "Car Removed";
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
