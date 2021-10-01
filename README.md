# citiesGuideSber
Решал задачу  загрузки и обработки справочника городов России, необходимую для реализации новой функциональности СберБизнеса.

Поэтапно добавлял новую функциональность в разрабатываемый модуль автоматизированной системы.

* Выбрал такую структуру для решения задачи: класс модели города, класс-точка входа в программу и утильный класс с разными реализациями поставленных задач. 

* Из файла с текстовой информацией о городах России считал данные через Scanner, добавив в список объектов городов. 

* Организовал сортировку городов по имени в алфавитном порядке через лямбда и через компаратор и сортировку по федеральному округу и по имени в разрезе ФО.

* Представил 2 реализации вывода количества городов в регионе через простой цикл и через Stream API.
