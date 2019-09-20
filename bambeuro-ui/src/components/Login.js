import NewUser from "./NewUser";
import LoginWithName from "./LoginWithName";
import React, { Component } from "react";
import "./Login.css";

class Login extends Component {
  state = {};

  componentDidMount() {
    setInterval(this.getUserCount, 250);
  }

  getUserCount = () => {
    fetch("/userTotal")
      .then(response => response.text())
      .then(message => {
        this.setState({ message: message });
      });
  };

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">There are {this.state.message} users</h1>
          <NewUser />
          <LoginWithName logInHandler={this.props.logInHandler} />
        </header>
      </div>
    );
  }
}

export default Login;
