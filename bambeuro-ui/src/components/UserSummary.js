import React, { Component } from "react";
import LoginRedirect from "./LoginRedirect";

class UserSummary extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userInfo: "",
      isLoggingOut: false,
      recipientName: "Recipient's name",
      transferAmount: "0.00"
    };
  }
  componentDidMount() {
    this.getUserInfo();
  }

  getUserInfo() {
    fetch("/users/" + this.props.userId)
      .then(response => response.json())
      .then(userInfo => {
        this.setState({ userInfo: userInfo });
      });
  }

  handleClick = () => {
    this.setState({
      isLoggingOut: true
    });
  };

  handleTransferClick = () => {
    fetch("/users/" + this.state.userId + "/transaction", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        recipient: this.state.recipientName,
        value: this.state.transferAmount
      })
    });
    this.getUserInfo();
  };

  handleRecipientChange = event => {
    this.setState({ recipientName: event.target.value });
  };

  handleAmountChange = event => {
    this.setState({ transferAmount: event.target.value });
  };

  render() {
    if (this.state.isLoggingOut === true) {
      return <LoginRedirect />;
    }
    return (
      <div>
        <h1>User Summary:</h1>
        <p>Name: {this.state.userInfo.name}</p>
        <p>Balance: {this.state.userInfo.bambeuroBalance}</p>
        <p>Transactions: {this.state.userInfo.transactions}</p>
        <form>
          <input
            type="text"
            value={this.state.recipientName}
            onChange={this.handleRecipientChange}
          />
          <input
            type="text"
            value={this.state.transferAmount}
            onChange={this.handleAmountChange}
          />
          <button type="button" onClick={this.handleTransferClick}>
            Send Money
          </button>
        </form>
        <button onClick={this.handleClick}>Logout</button>
      </div>
    );
  }
}

export default UserSummary;
