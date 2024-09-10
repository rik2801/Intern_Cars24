import React from 'react';
import Agentbox from './Agentbox';
import agentimage1 from '../../images/s1.png';
import agentimage2 from '../../images/s2.png';
import agentimage3 from '../../images/s3.png';


function Agent() {
    return (
        <div className='agent'>
            <div className='a-heading'>
                <h1>Customer Testimonial</h1>
                <p>What they'r saying</p>
                 </div>
                 <div className='b-container'>
                     <Agentbox image={agentimage1} name="Adam Nil"/>
                     <Agentbox image={agentimage2} name="Vijay Shankar"/>
                     <Agentbox image={agentimage3} name="Moen Ali"/>
                     
                     </div> 
            
        </div>
    )
}

export default Agent;
