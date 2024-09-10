import React from 'react';
import aboutimage from '../../images/About.jpg';

function About() {
    return (
        <div className='about'>
            <div className='about-model'>
                <img src={aboutimage} alt='about image'/>
                
                </div> 
                <div className='about-text'>
                    <h2>We are the best<br/>Car selling company</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing.</p>
                
                <button>View more Details</button>
                </div>
            
        </div>
    )
}

export default About;
