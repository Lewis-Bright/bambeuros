import React, { Component } from "react";
import { Redirect } from "react-router-dom";

class LoginWithName extends Component {
  constructor(props) {
    super(props);
    this.state = {
      searchUsername: "",
      userId: "",
      userLoggedIn: false
    };
  }

  handleClick = () => {
    fetch("/search/" + this.state.searchUsername)
      .then(response => response.text())
      .then(message => {
        this.props.logInHandler(message);
        this.setState({
          userId: message,
          userLoggedIn: true
        });
      });
  };

  handleChange = event => {
    this.setState({ searchUsername: event.target.value });
  };

  render() {
    if (this.state.userLoggedIn === true) {
      return <Redirect to={"/users/" + this.state.userId} />;
    }
    return (
      <React.Fragment>
        <form>
          <label htmlFor="username" />
          <input
            type="text"
            name="username"
            value={this.state.searchUsername}
            onChange={this.handleChange}
          />
          <button type="button" onClick={this.handleClick}>
            Login
          </button>
        </form>
      </React.Fragment>
    );
  }
}

export default LoginWithName;
