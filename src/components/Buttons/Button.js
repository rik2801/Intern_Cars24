import React from "react";
import { PrimaryButton } from "@fluentui/react/lib/Button";
import classes from "./../ComponentDesign.module.css";


/*
  Button can have two properties that changes the properties of button
  1. text     - defines the content over the button
  2. onClick  - defines the action to be performed on click
*/
class Button extends React.Component {
  constructor(props) {
      super(props);
    this.props = props;
  }

  render() {
    return (
      <div>
      <PrimaryButton className={classes.PrimaryButton} text={this.props.text} onClick={this.props.onClick} />
      </div>
    );
  }
}

export default Button;

