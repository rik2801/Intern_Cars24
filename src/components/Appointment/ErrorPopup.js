import React from "react";
import './style.css'
const ErrorPopup = props => {
  return (
    <div className="popup-box" style={{textAlign:"center"}}>
      <div className="box">
        <span className="close-icon" onClick={props.handleClose}>x</span>
        {props.content}
      </div>
    </div>
  );
};

export default ErrorPopup;