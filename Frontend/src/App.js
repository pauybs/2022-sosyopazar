import Router from "./components/Router";
import ApiProvider from "./api/ApiProvider"
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { trTR } from '@mui/material/locale';
import { SnackbarProvider } from 'notistack';
import 'mapbox-gl/dist/mapbox-gl.css';
import './main.css';
import 'moment/locale/tr'
import 'react-upload-gallery/dist/style.css' 

function App() {
  const theme = createTheme({
    palette: {
      type: 'light',
      primary: {
        main: '#5e503f',
        contrastText: '#f2f4f3',
      },
      secondary: {
        main: '#D4C8BE',
        contrastText: '#5e503f',
      },
      background: {
        default: '#f5f5f5',
      },
      divider: 'rgba(0,0,0,0.1)',
    },
    typography: {
      fontFamily: 'Rubik',
      htmlFontSize: 14,
      fontSize: 14,
    },
  },
    trTR,
  )
  return (
    <ThemeProvider theme={theme}>
      <SnackbarProvider maxSnack={3}>
        <ApiProvider>
          <Router />
        </ApiProvider>
      </SnackbarProvider>
    </ThemeProvider>
  );
}

export default App;
