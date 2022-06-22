import { CircularProgress } from '@mui/material'

const SuspenseFallback = () => {
    return (
        <div style={{
            width: "100vw",
            height: "100vh",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
        }}>
            <CircularProgress />
        </div>
    )
}

export default SuspenseFallback