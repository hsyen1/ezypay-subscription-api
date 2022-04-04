package com.subscription.demo;

import com.subscription.demo.common.SubscriptionType;
import com.subscription.demo.controller.dto.SubscriptionRequestDTO;
import com.subscription.demo.repository.PaymentSubscriptionRepository;
import com.subscription.demo.repository.entity.PaymentSubscription;
import com.subscription.demo.service.SubscriptionValidationService;
import com.subscription.demo.service.impl.SubscriptionServiceImpl;
import com.subscription.demo.service.model.SubscriptionCreationBO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest {

	@InjectMocks
	private SubscriptionServiceImpl subscriptionService;

	@Mock
	private SubscriptionValidationService mockValidationService;

	@Mock
	private PaymentSubscriptionRepository mockPaymentSubscriptionRepository;

	@Before
	public void init() {
		Mockito.doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			System.out.println("called with arguments: " + args[0]);
			return null;
		}).when(mockValidationService).validate(any(SubscriptionRequestDTO.class));

		Mockito.doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			System.out.println("called with arguments:" + args[0]);
			return null;
		}).when(mockPaymentSubscriptionRepository).save(any(PaymentSubscription.class));
	}

	@Test
	public void createSubscriptionTest() {
		SubscriptionCreationBO result = subscriptionService
				.create(mockRequestDTO(100, SubscriptionType.MONTHLY.getCode(),
						LocalDate.of(2022, 1, 1),
						LocalDate.of(2022, 2, 28),
						null, 1));

		System.out.println(result);
		List<String> expectedDatesInString = Stream.of("01/01/2022", "01/02/2022").collect(Collectors.toList());

		Assert.assertEquals(100.0, result.getAmount(), 0.0);
		Assert.assertEquals(SubscriptionType.MONTHLY, result.getSubscriptionType());
		Assert.assertTrue(result.getInvoiceDates().containsAll(expectedDatesInString));
	}

	@Test
	public void createDailySubscriptionTest() {
		SubscriptionCreationBO result = subscriptionService
				.create(mockRequestDTO(10, SubscriptionType.DAILY.getCode(),
						LocalDate.of(2022, 2, 28),
						LocalDate.of(2022, 3, 5), null, 28));

		System.out.println(result);
		List<String> expectedDatesInString = Stream
				.of("28/02/2022", "01/03/2022", "02/03/2022", "03/03/2022", "04/03/2022", "05/03/2022")
				.collect(Collectors.toList());

		Assert.assertEquals(10, result.getAmount(), 0.0);
		Assert.assertEquals(SubscriptionType.DAILY, result.getSubscriptionType());
		Assert.assertTrue(result.getInvoiceDates().containsAll(expectedDatesInString));
	}

	@Test
	public void createWeeklySubscriptionTest() {
		SubscriptionCreationBO result = subscriptionService
				.create(mockRequestDTO(40, SubscriptionType.WEEKLY.getCode(),
						LocalDate.of(2022, 3, 16),
						LocalDate.of(2022, 5, 5), "WEDNESDAY", 0));

		System.out.println(result);
		List<String> expectedDatesInString = Stream
				.of("16/03/2022", "23/03/2022", "30/03/2022", "06/04/2022", "13/04/2022", "20/04/2022", "27/04/2022", "04/05/2022")
				.collect(Collectors.toList());

		Assert.assertEquals(40, result.getAmount(), 0.0);
		Assert.assertEquals(SubscriptionType.WEEKLY, result.getSubscriptionType());
		Assert.assertTrue(result.getInvoiceDates().containsAll(expectedDatesInString));
	}

	@Test
	public void createMonthlySubscriptionTest() {
		SubscriptionCreationBO result = subscriptionService
				.create(mockRequestDTO(120, SubscriptionType.MONTHLY.getCode(),
						LocalDate.of(2022, 4, 16),
						LocalDate.of(2022, 7, 16), null, 16));

		System.out.println(result);
		List<String> expectedDatesInString = Stream
				.of("16/04/2022", "16/05/2022", "16/06/2022", "16/07/2022")
				.collect(Collectors.toList());

		Assert.assertEquals(120, result.getAmount(), 0.0);
		Assert.assertEquals(SubscriptionType.MONTHLY, result.getSubscriptionType());
		Assert.assertTrue(result.getInvoiceDates().containsAll(expectedDatesInString));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createInvalidSubscriptionTest() {
		Mockito.doThrow(new IllegalArgumentException("Invalid subscription type: QUARTERLY"))
				.when(mockValidationService).validate(any(SubscriptionRequestDTO.class));

		SubscriptionCreationBO result = subscriptionService
				.create(mockRequestDTO(120, "QUARTERLY",
						LocalDate.of(2022, 4, 16),
						LocalDate.of(2022, 7, 16), null, 16));

		System.out.println(result);
	}

	private SubscriptionRequestDTO mockRequestDTO(double amount, String subscriptionType,
												  LocalDate startDate, LocalDate endDate, String dayOfWeek,
												  int dayOfMonth) {
		SubscriptionRequestDTO requestDTO = new SubscriptionRequestDTO();
		requestDTO.setAmount(amount);
		requestDTO.setCustomerId(1);
		requestDTO.setStartDate(startDate);
		requestDTO.setEndDate(endDate);
		requestDTO.setSubscriptionType(subscriptionType);
		requestDTO.setDayOfMonth(dayOfMonth);
		requestDTO.setDayOfWeek(dayOfWeek);
		return requestDTO;
	}

}
