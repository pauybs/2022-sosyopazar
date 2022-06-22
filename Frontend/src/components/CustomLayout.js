import Navbar from './Navbar'
import { Container } from '@mui/material'
import Footer from './Footer';

const CustomLayout = ({ children }) => {


    return (
        <Container>
            <Navbar />
            <section className='defaultSection'>
                {children}
            </section>
            <Footer />
        </Container >
    )
}
export default CustomLayout;