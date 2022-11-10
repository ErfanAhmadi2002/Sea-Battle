package shared.events;

import shared.responses.Response;

public interface EventVisitor {
    Response checkLogin (LoginEvent loginEvent);

    Response createAccount (RegistrationEvent registrationEvent);

    Response watchProfile (int authToken);

    Response createNewBoard(CreateNewBoardEvent createNewBoardEvent);

    Response boardIsReady (BoardIsReadyEvent boardIsReadyEvent);

    Response getGameState (GetGameStateEvent getGameStateEvent);

    Response getLiveGameState (GetLiveGameStateEvent getLiveGameStateEvent);

    Response clickOnBoard (ClickOnBoardEvent clickOnBoardEvent);

    Response getGamesList (GetGamesListEvent getGamesListEvent);

    Response getLiveScore (GetLiveScoreEvent getLiveScoreEvent);
}
