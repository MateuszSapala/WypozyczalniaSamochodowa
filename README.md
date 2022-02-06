# WypozyczalniaSamochodowa
##Konfiguracja

Po uruchomieniu Intellija należy uruchomić MySQL Workbench i utworzyć bazę np: wypozyczalnia_samochodow.
W Projekcie należy przejść do folderu "resources" następnie kliknąć **application.properties** i zmienić:
wartości spring.datasource.username=root na użytkownika którego mamy przypisanego w bazie oraz spring.datasource.password=password na swoje hasło do bazy

Upewnić się iż nazwa bazy zgadza się z nowo utworzoną bazą
spring.datasource.url=.spring.datasource.url=jdbc:mysql://localhost:3306/**wypozyczalnia_samochodow**?useSSL=false&serverTimezone=CET. 
Po uruchomieniu programu tabele w bazie danych tworzą się i uzupełniają automatycznie.

##Uruchamianie
Klikamy zbuduj następnie przechodzimy do  klasy "Start" i  uruchamiamy  program.
Program składa się z 6 formatek : 
-KlientForm
-KlientZmianaHasla
-KlientZmianaLoginu
-Logowanie
-Rejestracja
-Main w którym jest  tabbedPane zawierający :
-Pracownicy
-Klienci
-Auta
-Rezerwacje
-GodzinyPracy

##Funkcje i cel
Program zrealizowany jest dla wypożyczalni samochodowej.
Program umożliwia zalogowanie się na konto pracownika oraz klienta.
W programie możemy dodać pracownika, edytować go oraz usunąć go.
Możemy wyszukać klienta oraz sprawdzić jego rekordy.
Możemy dodać samochód do bazy, usunąć go lub edytować istniejący.
Możemy dodać rezerwacje lub usunąć.
Możemy dodać godziny Pracy pracownika, zmodyfikować je lub usunąć.
Logując się możemy zerknąć do bazy i sprawdzić potrzebne nam dane.
W bazie danych tabeli pracownik znajdują się loginy i hasła dla pracowników, natomiast w tabeli klient znajdują się loginy i hasła dla klientów.

##Dane do logowania
Login dla pracownika: admin
Hasło dla pracownika: pass123!

Login dla klienta: user1
Hasło dla klienta: Passuser1!

