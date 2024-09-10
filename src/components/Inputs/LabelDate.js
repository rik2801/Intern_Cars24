import React from "react";
import classes from "./../ComponentDesign.module.css";

class LabelDate extends React.Component {
  constructor(props) {
    super();
    this.props = props;
  }

  state = {
    currentDate: "",
  };

  sendValue = (e) => {
    this.setState({ currentDate: e.target.value });
    this.props.onChange(e.target.value);
  };

  render() {
    return (
      <div className={classes.LabelDate} style={{textAlign: this.props.align}}>
        <p
          className={classes.LabelDate_label}
          style={{ fontSize: this.props.fontSize}}
        >
          {this.props.label}
        </p>
        <input
          className={classes.LabelDate_date}
          type="date"
          min={this.props.minDate}
          max={this.props.maxDate}
          required={this.props.isRequired}
          onChange={this.sendValue}
        />
      </div>
    );
  }
}

export default LabelDate;
