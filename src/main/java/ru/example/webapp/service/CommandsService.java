package ru.example.webapp.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandsService {

        private final List<String> listAllCommands;

        private final List<String> listYBotCommands;

        public List<String> getListAllCommands() {
            return listAllCommands;
        }

        public List<String> getListYBotCommands() {
            return listYBotCommands;
        }

        public CommandsService() {
            listAllCommands = new ArrayList<>();
            listAllCommands.add("//room create {Название комнаты}");
            listAllCommands.add("//room remove {Название комнаты}");
            listAllCommands.add("//room rename {Название комнаты} {Новое название комнаты}");
            listAllCommands.add("//room connect {Название комнаты} -l {login пользователя}");
            listAllCommands.add("//room disconnect {Название комнаты} -l {login пользователя} -m {Количество минут}");
            listAllCommands.add("//user rename {Старый login пользователя} {Новый логин пользователя}");
            listAllCommands.add("//user ban -l {login пользователя} -m {Количество минут}");
            listAllCommands.add("//yBot find -v " +
                    "-l {название канала}||{название видео} " +
                    "-v(выводит количество текущих просмотров) " +
                    "-l(выводит количество лайков под видео)");
            listAllCommands.add("//yBot help");
            listYBotCommands = new ArrayList<>();
            listYBotCommands.add("//yBot find -k -l {название канала}||{название видео} " +
                    "-v(выводит количество текущих просмотров) " +
                    "-l(выводит количество лайков под видео)");

        }

}
