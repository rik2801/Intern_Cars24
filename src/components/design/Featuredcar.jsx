import React from 'react';
import Bfeatured from './Bfeatured';
import bimage1 from '../../images/d1 (1).jpg';
import bimage2 from '../../images/d1 (2).jpg';
import bimage3  from '../../images/d1 (3).jpg';
import bimage4 from '../../images/d1 (4).jpg';

function Featuredcar() {
    return (
        <div className='product'>
            <div className='p-heading'>
                <h3>Featured Car</h3>
                <p>We have the best used car at a nominal price and you can easily get with a huge discount</p>
            </div>
            <div className='product-container'>
                <Bfeatured image={bimage1} name="Tata Safari" price="₹14.69L-21.46L"/>
                <Bfeatured image={bimage2} name="Honda city" price="₹11.02L-14.97L"/>
                <Bfeatured image={bimage3} name="Toyota Fortuner" price="₹13.36L-38.32L"/>
                
            </div>
           
            
        </div>
    )
}

export default Featuredcar;
