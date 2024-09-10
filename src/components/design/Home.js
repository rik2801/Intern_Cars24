import Header from './Header'
import Howitwork from './Howitwork'
import About from './About'
import Agent from './Agent'
import Featuredcar from './Featuredcar'
import Contact from './Contact' 
import '../design/Designing.css'
function Home(){
    return (
    <div className="App">
      <Header/>
      <Howitwork/>
      <About/>
      <Agent/>
      <Featuredcar/>
     <Contact/>
     
      
  
    </div>
  );
  }
  export default Home;