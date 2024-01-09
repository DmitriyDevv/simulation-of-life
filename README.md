<a name="description"></a>

[Описание](#description)  
[Технологии](#technologies)  
[Быстрый старт](#quickStart)

---

### Описание:

**Симуляция поведения хищников и травоядных**

Условно бесконечная и упрощенная симуляция взаимодействия между хищниками и травоядными.
Хищники "охотятся" за травоядными, а травоядные — за травой :)

**Отображение**

Для отображения используется
библиотека [Swing](https://docs.oracle.com/javase/8/docs/technotes/guides/swing/index.html).

![view-simulation-window.png|500](gitsrc/view-simulation-window.png)

**Поиск пути**

Используется алгоритм [A*](https://ru.wikipedia.org/wiki/A*). При каждом шаге существо теряет HP; для разных существ
этот коэффициент разный. Существо может восполнить HP: хищники за счет травоядных, травоядные - за счет травы.

**Обновление карты**

Когда количество существ уменьшается до одного, карта заселяется случайным количеством существ в диапазоне от 1 до 10.

**Более детальное описание проекта:**
[тык](https://zhukovsd.github.io/java-backend-learning-course/Projects/Simulation/)

---

<a name="technologies"></a>

### Технологии:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

---

<a name="quickStart"></a>

### Быстрый старт:

**Клонируйте репозиторий:**

```
https://github.com/DmitriyDevv/simulation-of-life.git
```

**Для сборки проекта введите в консоль команду:**

```
mvn clean package
```

**В папке target появится файл с расширение jar.
Чтобы запустить программу, введите в консоль:**

```
java -jar имя файла
```

---