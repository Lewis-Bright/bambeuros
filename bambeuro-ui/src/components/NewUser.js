import React, { Component } from "react";

class NewUser extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "" };
  }

  handleClick = () => {
    fetch("/user", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        name: this.state.username
      })
    });
  };

  handleChange = event => {
    this.setState({ username: event.target.value });
  };

  render() {
    return (
      <React.Fragment>
        <form>
          <label htmlFor="username" />
          <input
            type="text"
            name="username"
            value={this.state.username}
            onChange={this.handleChange}
          />
          <button type="button" onClick={this.handleClick}>
            Create User
          </button>
        </form>
      </React.Fragment>
    );
  }
}

export default NewUser;
