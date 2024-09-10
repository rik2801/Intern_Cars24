import React, { useState } from 'react';
import Popup from './ErrorPopup';
import AddAppointment from './AddAppointment'

function UpdateAppointmentWindow() {
  const [isOpen, setIsOpen] = useState(false);
  const [errorMsg, setErrorMsg] = useState();
  const [errorLogo, setErrorLogo] = useState();
  const togglePopup = (e) => {
    setErrorMsg(e.errorMsg);
    setErrorLogo(e.errorLogo);
    setIsOpen(!isOpen);

  }

  return <div>
    <AddAppointment showError={togglePopup}/>
    {isOpen && <Popup
      content={<>
        <h1 style={{display:"inline"}}>
            {errorLogo}
        </h1>
        <p style={{display:"inline"}}>&nbsp;&nbsp;&nbsp;{errorMsg}</p>
      </>}
      handleClose={togglePopup}
    />}
  </div>
}

export default UpdateAppointmentWindow;