package library;

import library.lv3.usecase.AddNewBookUseCase;
import library.lv3.usecase.GetAllBooksUseCase;
import library.lv4.controller.Controller;
import library.lv4.controller.console.ConsoleController;
import library.lv4.controller.tgbot.TelegramBotController;
import library.lv4.controller.tgbot.action.GetAllBooksAction;
import library.lv4.controller.tgbot.action.RouterAction;
import library.lv5.impl.infrastructure.ConsoleIO;
import library.lv5.impl.repo.MapBookRepository;
import library.lv5.impl.repo.PgBookRepository;
import library.lv5.impl.service.StubEmailService;
import library.lv5.impl.service.YandexMailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        build().start();
    }

    private static Controller build() {
//        var bookRepo = new MapBookRepository();
        var bookRepo = new PgBookRepository();

        var io = new ConsoleIO();

        var emailService = new StubEmailService(io);
//        var emailService = new YandexMailService();

        var getAllBooksUseCase = new GetAllBooksUseCase(bookRepo);
        AddNewBookUseCase addNewBookUseCase = new AddNewBookUseCase(
                bookRepo,
                emailService);

        var controller = new ConsoleController(
                io,
                getAllBooksUseCase,
                addNewBookUseCase);
//        GetAllBooksAction.Factory.init(getAllBooksUseCase);
//        var controller = new TelegramBotController();

        return controller;
    }
}
