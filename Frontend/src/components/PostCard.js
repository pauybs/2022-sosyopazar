import { Card, Avatar, Typography, Divider } from '@mui/material'
import { Box } from '@mui/system'
import moment from 'moment'
import { BASE_URL } from '../api/ApiProvider'

const PostCard = ({ postData }) => {
    return (
        <Card sx={{ p: 3, boxShadow: "0px 4px 24px rgba(0, 0, 0, 0.1)", borderRadius: "20px" }}>
            <Box sx={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
                <Box sx={{ display: "flex", alignItems: "center" }}>
                    <Avatar sx={{ width: 42, height: 42 }} src={`${BASE_URL}/api/seller/${postData?.contentOwner?.id}/profilePhoto`} />
                    <Box sx={{ ml: 2 }}>
                        <Typography variant='h6' sx={{ margin: 0, fontSize: '1.1rem' }}>{postData?.contentOwner?.storeName}</Typography>
                        <Typography variant='p' sx={{ margin: 0, fontWeight: 300 }}>{moment(postData?.createdDateTime).startOf().fromNow()}</Typography>
                    </Box>
                </Box>
                {/* <Button sx={{ justifySelf: "end" }}>TEKLİF VER</Button> */}
            </Box>
            <Divider sx={{ my: 2 }} />
            <Typography variant='p'>
                {postData?.content}
            </Typography>
        </Card >
    )
}

export default PostCard;