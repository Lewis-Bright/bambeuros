import React, { Component } from "react";
import LoginRedirect from "./LoginRedirect";

class UserSummary extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userInfo: "",
      isLoggingOut: false
    };
  }
  componentDidMount() {
    fetch("/users/" + this.props.userId)
      .then(response => response.text())
      .then(userInfo => {
        this.setState({ userInfo: userInfo });
      });
  }
  handleClick = () => {
    this.setState({
      isLoggingOut: true
    });
  };

  render() {
    if (this.state.isLoggingOut === true) {
      return <LoginRedirect />;
    }
    return (
      <div>
        <h1>User Summary:</h1>
        <p>{this.state.userInfo}</p>
        <button onClick={this.handleClick}>Logout</button>
      </div>
    );
  }
}

export default UserSummary;
