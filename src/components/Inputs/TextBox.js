import React from "react";
import classes from "./../ComponentDesign.module.css";

class TextBox extends React.Component {
  constructor(props) {
    super();
    this.props = props;
  }

  sendValue = (e) => {
    this.props.onChange(e.target.value);
  };

  render() {
    return( <div className={classes.TextBox}>
        <input className={classes.TextBox_text}
        type={this.props.type} 
        required = {this.props.isRequired}
        placeholder = {this.props.placeHolder}
        onBlur = {this.sendValue}
        />

    </div> )
}
}

export default TextBox;
