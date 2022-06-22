import { Box, Typography, } from '@mui/material'

const Footer = () => {

    return (
        <footer>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <Typography className='logo'>
                    SosyoPazar
                </Typography>
                <Typography variant='body2'>
                    Mustafa Cavlak tarafından tez projesi için yapılmıştır.
                </Typography>
            </Box>
        </footer >
    )
}
export default Footer;