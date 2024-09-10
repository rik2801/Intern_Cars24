import React from 'react';

function Agentbox(props) {
    return (
        <div className='a-box'>
            <div className='a-b-img'>
                <img src={props.image} alt=''/>
            </div>
            <div className='a-b-text'>
                <h3>{props.name}</h3>
                <p>"They have a variety of cars for selling and buying at a nominal price "</p>
                
            </div>
            
        </div>
    )
}

export default Agentbox
