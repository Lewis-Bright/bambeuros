import React, { Component } from "react";
import { Route, Switch } from "react-router-dom";
import Login from "./components/Login";
import LoginRedirect from "./components/LoginRedirect";
import UserSummary from "./components/UserSummary";

class App extends Component {
  state = {
    loggedInUserId: ""
  };

  logInHandler = loginId => {
    this.setState({
      loggedInUserId: loginId
    });
  };

  render() {
    return (
      <div>
        <Switch>
          <Route
            path="/login"
            render={() => <Login logInHandler={this.logInHandler} />}
          />
          <Route exact path="/" component={LoginRedirect} />
          <Route
            path="/users/"
            render={() => <UserSummary userId={this.state.loggedInUserId} />}
          />
        </Switch>
      </div>
    );
  }
}

export default App;
