import React, { Component } from "react";

class UserSummary extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userInfo: ""
    };
  }
  componentDidMount() {
    fetch("/users/" + this.props.userId)
      .then(response => response.text())
      .then(userInfo => {
        this.setState({ userInfo: userInfo });
      });
  }
  render() {
    return (
      <div>
        <h1>User Summary:</h1>
        <p>{this.state.userInfo}</p>
      </div>
    );
  }
}

export default UserSummary;
