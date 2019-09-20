import React, { Component } from "react";
import { Redirect } from "react-router-dom";

class LoginRedirect extends Component {
  render() {
    return (
      <div>
        <Redirect to="/login" />
      </div>
    );
  }
}

export default LoginRedirect;
