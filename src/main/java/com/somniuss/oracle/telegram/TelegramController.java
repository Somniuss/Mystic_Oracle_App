package com.somniuss.oracle.telegram;

import com.somniuss.oracle.dto.TelegramUserDto;
import com.somniuss.oracle.entity.Prediction;
import com.somniuss.oracle.mapper.TelegramUserMapper;
import com.somniuss.oracle.service.PredictionService;
import com.somniuss.oracle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telegram")
public class TelegramController {

	private final PredictionService predictionService;
	private final UserService userService;
	private final TelegramUserMapper telegramUserMapper;

	@Autowired
	public TelegramController(PredictionService predictionService, UserService userService,
			TelegramUserMapper telegramUserMapper) {
		this.predictionService = predictionService;
		this.userService = userService;
		this.telegramUserMapper = telegramUserMapper;
	}

	@GetMapping(value = "/next-prediction", produces = MediaType.APPLICATION_JSON_VALUE)
	public Prediction getNextPrediction() {
		return predictionService.getRandomPrediction();
	}

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TelegramUserDto registerTelegramUser(@RequestBody TelegramUserDto telegramUserDto) {

		var user = telegramUserMapper.fromTelegramDto(telegramUserDto);

		userService.saveUser(user);

		return telegramUserMapper.toTelegramDto(user);
	}
}
