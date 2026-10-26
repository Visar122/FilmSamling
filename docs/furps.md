# FURPS – vores filmsamling

|   | Krav                                                                                   | Opfyldt?                                     | Hvor                                                   |
|---|----------------------------------------------------------------------------------------|----------------------------------------------|--------------------------------------------------------|
| F | Man kan oprette, vise, søge, rette og slette film                                      | Ja                                           | US1–US6, `UserInterface` og `MovieCollection`          |
| F | Man kan søge på en del af en titel, uanset store og små bogstaver                      | Ja                                           | US4, `MovieCollection.searchMovies`                    |
| F | Man kan markere en film som set, og datoen vises sammen med filmen                     | Ja                                           | US8, `Movie.markAsWatched`, `Controller.markAsWatched` |
| F | Man kan finde de film, man ikke har set i et antal dage eller aldrig har set           | Ja                                           | US9, `MovieCollection.getMoviesNotWatchedSince`        |
| U | Man kan trykke Enter for at beholde en værdi, når man redigerer                        | Ja                                           | US5, `UserInterface.EditMovie`                         |
| U | Datoer vises som dd-MM-yyyy, og en film, der aldrig er set, viser "aldrig" – ikke null | Ja                                           | US8, `UserInterface.lastWatchedText`                   |
| U | Programmet spørger, om man er sikker, før en film slettes                              | Ja                                           | US6, `UserInterface.DeleteMovie`                       |
| R | Ukendte menuvalg giver en besked, og programmet går ikke ned                           | Ja                                           | US2, `UserInterface.StartProgram`                      |
| R | Programmet går ikke ned ved bogstaver i årstal, længde eller antal dage                | Nej – del 6                                  |                                                        |
| P | Filen skrives kun, hvis der er ændringer                                               | Nej – del 7                                  |                                                        |
| S | Datologikken i `Movie` og `MovieCollection` er dækket af unit tests                    | Ja                                           | `MovieTest`, `MovieCollectionTest`                     |
| S | Al logik uden for `UserInterface` er dækket af unit tests                              | Delvist – søg, redigér og slet mangler tests |                                                        |
| S | System.out og Scanner findes kun i `UserInterface`                                     | Ja                                           |                                                        |